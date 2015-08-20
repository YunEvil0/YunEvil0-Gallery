package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.xxx.common.Config;

public class TestExif2 {
	public static void main(String[] args) throws JpegProcessingException,
			IOException {
		try {
			File file = new File("/Users/YunEvil0/Downloads/tmp/IMG_0121.jpg");
			List<JpegSegmentMetadataReader> rList = new ArrayList<JpegSegmentMetadataReader>();
			rList.add(new ExifReader());
			Iterable<JpegSegmentMetadataReader> readers = rList;
			Metadata metadata = JpegMetadataReader.readMetadata(file, readers);
			System.out.println(Config.get().get("exif"));
			
			List<String> tagLsit = Arrays.asList(Config.get().get("exif").split(","));
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					if (tagLsit.contains(tag.getTagName())) {
						System.out.println("[" + tag.getTagName() + "]"
								+ tag.getDescription());
					}
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
