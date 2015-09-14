package test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.vo.TagVo;
import com.xxx.mongo.MongoUtil;

public class test2 {

	public static void main(String[] args) throws UnknownHostException {
		/*
		MongoUtil mongoUtil = MongoUtil.getInstance();
		Datastore ds = mongoUtil.getDS();
		Query<CommonFileDTO> query = ds.createQuery(CommonFileDTO.class).field("fileMD5").equal("fc6af584def01d39f2243e6dcc8c7986");
		CommonFileDTO commonFileDTO = query.get();
		
		List<TagVo> tag = new ArrayList<TagVo>();
		TagVo tagVo = new TagVo();
		tagVo.setTagContent("222");
		tagVo.setTagType("T222");
		tag.add(tagVo);
//		UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class).ad;
		UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class).addAll("bisAttr.tagList", tag, false);
		UpdateResults update = ds.update(query, ops);
		System.exit(0);
		*/
		
	}
}
