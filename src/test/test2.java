package test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.traveler54.gallery.vo.CommonFile;
import com.traveler54.gallery.vo.ImageBisInfoTagVo;
import com.traveler54.mongo.MongoUtil;

public class test2 {

	public static void main(String[] args) throws UnknownHostException {
		MongoUtil mongoUtil = MongoUtil.getInstance();
		Datastore ds = mongoUtil.getDS();
		Query<CommonFile> query = ds.createQuery(CommonFile.class).field("fileMD5").equal("32e8c9a3e2104df5717bfb4feacd24ce");
		
		List<ImageBisInfoTagVo> tag = new ArrayList<ImageBisInfoTagVo>();
		ImageBisInfoTagVo tagVo = new ImageBisInfoTagVo();
		tagVo.setTagContent("1");
		tagVo.setTagType("T1");
		tag.add(tagVo);
		UpdateOperations<CommonFile> ops = ds.createUpdateOperations(CommonFile.class).addAll("tagList", tag, false);
		UpdateResults update = ds.update(query, ops);
		System.exit(0);
	}
}
