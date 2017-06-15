package cn.yq.springmvc.entity.enums;

public enum Gender {
 Male("男"),Fmale("女");
 
 private String text;
 
  Gender(String text){
	 this.text=text;
 }
  
  public String getText(){
	  return text;
  }

}
