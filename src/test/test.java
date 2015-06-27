//package test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.JSON;
//import com.xxx.gallery.action.UploadFileStrategyBaseVo;
//
//public class test {
//
//	public static void main(String[] args) {
//		List<UploadFileStrategyBaseVo> voList=new ArrayList<UploadFileStrategyBaseVo>();
//		UploadFileStrategyBaseVo A0Vo=new UploadFileStrategyBaseVo();
//		A0Vo.setName("A1");
//		A0Vo.setFilePath("A1");
//		A0Vo.setFilePrefix("IMG");
//		A0Vo.setFileSizeMax(1*1024*1024);
//		A0Vo.setSizeMax(1*1024*1024);
//		A0Vo.setNeedWaterMark(false);
//		A0Vo.setSuffix(new ArrayList<String>());
//		A0Vo.getSuffix().add("png");
//		A0Vo.getSuffix().add("jpg");
//		A0Vo.getSuffix().add("gif");
//		A0Vo.setFileTypePreFix(new ArrayList<String>());
//		A0Vo.getFileTypePreFix().add("FFD8FF");//jpg
//		A0Vo.getFileTypePreFix().add("89504E47");//png
//		A0Vo.getFileTypePreFix().add("47494638");//gif
//		voList.add(A0Vo);
//		System.out.println(JSON.toJSONString(voList));
//		voList=JSON.parseArray(JSON.toJSONString(voList), UploadFileStrategyBaseVo.class);
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//	}
//}
