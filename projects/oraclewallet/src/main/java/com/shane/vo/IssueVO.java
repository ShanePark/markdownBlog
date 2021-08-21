package com.shane.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueVO {
	@NotNull @Min(0) private Integer issue_sid;
	@NotNull @Min(0) private Integer issue_no;
	@NotNull @Min(0) private Integer mem_no;
	 private Integer label_no;
	 private Integer milest_sid;
	@NotNull @Min(0) private Integer proj_no;
	@NotBlank@Size(max=200) private String issue_title;
	@Size(max=7) private String issue_create_date;
	@Size(max=7) private String issue_start_date;
	@Size(max=7) private String issue_end_date;
	@NotBlank@Size(max=1) private String issue_status;
	 private Integer issue_priority;
	 private Integer progress;
}
