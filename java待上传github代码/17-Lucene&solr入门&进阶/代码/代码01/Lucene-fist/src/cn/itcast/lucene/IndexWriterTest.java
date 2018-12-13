package cn.itcast.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexWriterTest {

	public static void main(String[] args) throws Exception {
//		1、指定索引库位置
		Directory directory = FSDirectory.open(new File("D:\\class297\\indexRepo"));
		
//		指定分词器
		Analyzer analyzer = new StandardAnalyzer();
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		
//		2、创建写入索引的对象
		IndexWriter indexWriter = new IndexWriter(directory, config);
		
//		3、获取源文档
		File srcFile = new File("D:\\ITCAST\\Lucene&solr\\Lucene&solr-day01\\资料\\上课用的查询资料searchsource");
		File[] listFiles = srcFile.listFiles();
		for (File file : listFiles) {
			Document doc = new Document();
//		     1、文件名称
			String fileName = file.getName();
			Field nameField = new TextField("name", fileName, Store.YES);
			doc.add(nameField);
//		     2、文件大小
			long fileSize = FileUtils.sizeOf(file);
			Field sizeField = new TextField("size", fileSize+"", Store.YES);
			doc.add(sizeField);
//		     3、文件路径
			String filePath = file.getPath();
			Field pathField = new TextField("path", filePath, Store.YES);
			doc.add(pathField);
//		     4、文件内容
			String fileContent = FileUtils.readFileToString(file);
			Field contentField = new TextField("content", fileContent, Store.YES);
			doc.add(contentField);
			
//			4、把文档写入索引库
			indexWriter.addDocument(doc);
		}
		
//		5、关闭资源
		indexWriter.close();

		
	}

}
