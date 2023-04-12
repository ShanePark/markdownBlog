# GAIA 알람 시스템을 만들기 위해 구축한 여러가지 모듈 소개와 과정

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/gaia-alarm.assets/img-20230412214623571.webp width=750 height=790 alt=1>



드디어 알람 시스템을 어느 정도 완성 했습니다. 

사실 처음에는 Elastic search로 알람 기능을 구현 하려고 했지만, 이제 시간이 얼마 남지 않은 상태에서 팀 내 많은 이슈가 발생해서 쿼리부분에서 어느정도 막힌다 싶을때 급하게 Oracle 로 일단 턴 했습니다.

Elastic Search를 사용하려고 elasticUtil 모듈을 만들어 Bean에 등록 했고, 그 유틸을 사용할 수 있는 나름의 Dao와 Service를 만들어서 했는데, 알람이 확인이 되면 모두 확인되었다는 update를 쳐야 하는데, '조회' 와 '등록' 단 두가지로 Elastic Search 활용을 준비하던 터라 거기에서 막혔습니다. 간단한 log 쌓기나 메시지 관련 기능이라도 Elastic Search를 활용해 저장 하려고 합니다.

 

간단하게 지금은 삭제되었지만, 엘라스틱서치를 활용해 알람 데이터를 작성 하려고 등록해 두었던 코드를 공개하겠습니다

java

```java
@Service
public class AlarmServiceImpl implements AlarmService {
	
	@Inject
	private AlarmDao dao;
 
	@Override
	public ServiceResult insertIssueCommentAlarm(IssueVO issue) {
		IssueHistoryVO issueHistory = issue.getHistoryList().get(0);
		Map<String, Object> alarm = new HashMap<>();
		alarm.put("alarm_type", "IC");
		alarm.put("url", issue.getUrl());
		alarm.put("proj_usernick", issueHistory.getHistoryWriter().getMem_nick());
		alarm.put("issue_title",issue.getIssue_title());
		alarm.put("issue_his_cont",issueHistory.getIssue_his_cont());
		int result = dao.insertAlarm(issue.getMem_no(), alarm);
		
		return result==1 ? ServiceResult.OK : ServiceResult.FAIL;
	}
 
}
```

AlarmService 입니다.

 

java

```java
@Component
public class AlarmDaoImpl implements AlarmDao {
	
	@Inject
	private ElasticUtil elastic;
	
	public int insertAlarm(int mem_no, Map<String,Object> alarm) {
		alarm.put("mem_no", mem_no);
		String index = "alarm";
		return elastic.insert(index, null, alarm);
	}
	
	public List<Map<String, Object>> getAlarmList(int mem_no){
		String index = "alarm";
		
		Map<String,Object> query = new HashMap<>();
		query.put("mem_no",mem_no);
		
		Map<String,SortOrder> sort = new HashMap<>();
		sort.put("date",SortOrder.DESC);
		
		return elastic.simpleSearch(index, query, sort);
		
	}
	
}
```

AlarmDao 입니다.

 

java

```java
@Component
public class ElasticUtil {
	private RestClientBuilder restClientBuilder;
	
	private ElasticUtil() {
        Properties properties = new Properties();
		try {	// dbinfo.properties에서 접속 정보 받아옵니다.
			properties.load(Resources.getResourceAsReader("best/gaia/db/dbinfo.properties"));
		} catch (IOException e) {}
        String hostname = properties.getProperty("el.url");
        int port = Integer.parseInt(properties.getProperty("el.port"));
        HttpHost host = new HttpHost(hostname, port);
        restClientBuilder = RestClient.builder(host);
	};
	
	public List<Map<String,Object>> simpleSearch(
			String index
			, Map<String,Object> query
			, Map<String,SortOrder> sort
			){
		/*
		 * search API 참고 주소
		 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-search.html
		 */
		
		// search에 index 조건 걸기
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		// query에 있는 셋 쿼리 조건으로 걸기
		for(String key : query.keySet()) {
			searchSourceBuilder.query(QueryBuilders.matchQuery(key, query.get(key)));
		}
		
		// sort 에 있는 셋을 정렬 조건으로 걸기
		for(String key : sort.keySet()) {
			searchSourceBuilder.sort(new FieldSortBuilder(key).order(sort.get(key)));
		}
		
		searchRequest.source(searchSourceBuilder);
		
		List<Map<String,Object>> list = new ArrayList<>();
		try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits searchHits = response.getHits();
			for(SearchHit hit : searchHits) {
				Map<String, Object> sourceMap = hit.getSourceAsMap();
				list.add(sourceMap);
			}
		} catch (IOException e) {}
		
		return list;
		
	}
	
	public Map<String,Object> getReponse(String index, String id){
		
		GetResponse response = null; 
		
		GetRequest getRequest = new GetRequest(index, id);
		RequestOptions options = RequestOptions.DEFAULT;
		try (RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
			response = client.get(getRequest, options);
		} catch (IOException e) {}
		
		return response.getSourceAsMap();
	}
	
	public int insert(String index, String id, Map<String, Object> data ){
		IndexResponse response = null;
		try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
			data.put("date", LocalDateTime.now());
			XContentBuilder xContent = XContentFactory.jsonBuilder().map(data);
			String jsonBody = Strings.toString(xContent);
			
			IndexRequest indexRequest = new IndexRequest(index).id(id).source(jsonBody, XContentType.JSON);
			response = client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {}
		
		return response.getShardInfo().getSuccessful();
	}
 
}
```

그리고 Elastic Search 엘라스틱을 최대한 팀원들이 쉽게 활용 할 수 있게 하려고 만든 유틸 모듈 입니다.

필요한 파라미터와 그에 대응하는 return 타입만 이해하면 쉽게 사용 할 수 있도록 작성해 두었습니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/gaia-alarm.assets/img-20230412214623541.webp)



알람 테이블의 형태는 위와 같습니다. 다만, ALARM_CONT 를 MAP을 JSON 형식으로 담아 여러가지 알람 타입에 모두 대응 할 수 있도록 하였습니다. ALARM_CONT에는 json 데이터가 들어가다 보니 사이즈를 넉넉하게 잡아 두었습니다.

알람 데이터는 다음과 같은 형식으로 저장이 됩니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/gaia-alarm.assets/img-20230412214623564.webp)



 

해당 형식으로 저장 하기 위해 약간의 로직이 필요해 service 를 먼저 거칩니다.

java

```java
	@Override
	public ServiceResult insertIssueCommentAlarm(IssueVO issue) {
		IssueHistoryVO issueHistory = issue.getHistoryList().get(0);
		AlarmVO alarm = new AlarmVO();
		alarm.setMem_no(issue.getMem_no());
		alarm.setAlarm_type("IC");
		
		// 알램 내용을 맵으로 만들고
		Map<String, Object> alarmContent = new HashMap<>();
		alarmContent.put("url", issue.getUrl());
		alarmContent.put("proj_user_nick", issueHistory.getHistoryWriter().getMem_nick());
		alarmContent.put("issue_title",issue.getIssue_title());
		alarmContent.put("issue_his_cont",issueHistory.getIssue_his_cont());
		
		// 해당 알람 내용은 json 형태로 alarm_cont에 저장한다
		String alarm_cont = new Gson().toJson(alarmContent);
		alarm.setAlarm_cont(alarm_cont);
 
		int result = dao.insertAlarm(alarm);
		
		return result==1 ? ServiceResult.OK : ServiceResult.FAIL;
	}
```

issue 에 댓글을 달때 해당하는 알람을 전송하는 서비스 입니다.

issue 객체에 필요한 데이터들을 담아 넘어왔고, 해당 데이터들을 토대로 alarm 객체의 alarm_cont 프로퍼티에 json 형태로 map을 저장해 담습니다. dao에선 담겨있는 property 들을 단순하게 insert 합니다.

 

이제 이렇게 담은 데이터들을 받아와, 원하는 형식으로 출력을 해 주어야 하는데요.

그에대한 format을 코드 수정 없이 변경 가능 하도록 데이터 베이스 에서 공통 코드로 관리합니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/gaia-alarm.assets/img-20230412214623589.webp)



공통 코드 테이블에 COM_CODE 와 그에 상응하는 COM_CODE_DESC에는 해당 알람의 텍스트 포맷과 스타일이 이 담겨 있습니다.

 

그래서 알람을 불러올때도 로직이 필요합니다.

 

특정 code group에 해당하는 모든 코드에 대한 정보를 받아 와서, COM_CODE를 key로, COM_CODE_DESC를 value로 매칭해 map 으로 만들어 반환하는 메서드를 먼저 만들었습니다.

java

```java
	@Override
	public Map<String, String> getCodeMap(String com_code_grp) {
		List<CommonCodeVO> codeList = dao.selectCodesByGroup("AT");
		Map<String, String> codeMap = new HashMap<>();
		for(CommonCodeVO vo : codeList) {
			codeMap.put(vo.getCom_code(), vo.getCom_code_desc());
		}
		return codeMap;
	}
```

 

또한, #{ } 형식으로 만든 place holder에 해당하는 값들을 넣어 String을 새로 만들어 주는 함수도 만들었습니다.

java

```java
	public static String elMapper(Map<String,String> data, String result ) {
		Set<String> keySet = data.keySet();
		for(String key : keySet) {
			result = result.replace(
					String.format("%s%s%s", "#{",key,"}")
					, data.get(key));
		}
		return result;
```

정규식을 이용해 만드는게 참 좋을텐데, 일단 빨리 구현하고 싶은 맘에 일단 replace를 활용했습니다.

 

위에서 만든 메서드들을 이용해 알람들을 불러오는 서비스를 만들었습니다. 코드는 아래와 같습니다.

java

```java
	@Override
	public List<AlarmVO> selectAlarmList(int mem_no) {
		
		// 일단 회원에게 해당하는 alarm 데이터들을 불러 옵니다.
		List<AlarmVO> list = dao.getAlarmList(mem_no);
		
		// 알람 데이터 가공을 위해 알람 타입에 해당하는 codeMap 을 받아옵니다.
		Map<String, String> codeMap = commonService.getCodeMap("AT");
		
		// alarm list를 하나하나 반복문 돌려가며 가공합니다.
		for(AlarmVO alarm : list) {
			// 알람 cont에 json 형태로 담아둔 데이터를 Map으로 꺼내옵니다.
			Map<String,String> alarmData = new Gson().fromJson(alarm.getAlarm_cont(), Map.class);
			
			// 해당 알람의 type에 해당하는 com_code_desc를 받아 옵니다.
			String com_code_desc = codeMap.get(alarm.getAlarm_type());
			
			// alarm_cont 에 출력해줄 알람 데이터를 만들어 줍니다.
			String alarm_cont = ParsingUtil.elMapper(alarmData, com_code_desc);
			alarm.setAlarm_cont(alarm_cont);
			
			// 마지막으로 alarm_cont에 url이 있다면, 담아서 보내줍니다. 
			alarm.setUrl(alarmData.get("url").toString());
			
		}
		
		// 데이터 가공이 끝난 Alarm 리스트를 반환합니다.
		return list;
	}
```

코드맵을 받아오고, 그 코드맵에서 해당하는 com_code_desc 

java

```java
<span style="color:blue">#{issue_title}</span> 이슈에 #{proj_user_nick}님이 댓글을 달았습니다 : #{issue_his_cont}Copy
```

을 찾아옵니다. 그래서 elMapper에 alarm_cont에 담아둔 json 형태의 데이터들을 Map으로 만든 alarmData를 넣고, 포맷으로 사용할 String을 넣으면 최종적으로 알람으로 표현할 문자열을 만들어 줍니다.

java

```java
{"issue_his_cont":"알람전송 마이바티스","issue_title":"이슈 작성 테스트","url":"/kkobuk/testproject/issue/112","proj_user_nick":"팀장 꼬북"}Copy
```

그럼 아래의 Data가 위의 place holder에 들어가 최종적으로 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/gaia-alarm.assets/img-20230412214623526.webp)



 

이렇게 생긴 문자열을 생성해 줍니다. javascript에서는 그냥 받아서 편하게 적당한 위치에 넣어주기만 하면 됩니다. 2 hours ago 부분은 moment.js 라이브러리를 사용했습니다.

 

처음 계획했던 대로 Elastic Search를 활용해 구현했으면 더 좋았겠지만, 잠자는 내내 머리속으로만 계획했던 로직이 실제 잘 작동하는 것을 확인하니 뿌듯하고 좋습니다.

 

처음 구현은 꽤 어려웠지만, 모듈화 시켜 두었기 때문에 유연하게 여러가지 상황에 맞는 알람을 쉽게 생성 할 수 있을 듯 합니다.