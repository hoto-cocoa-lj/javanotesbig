package cn.itcast.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IndexManager {
	
	IndexWriter indexWriter = null;
	
	@Before
	public void initIndexWriter() throws Exception{
//		1、指定索引库位置
		Directory directory = FSDirectory.open(new File("D:\\class297\\indexRepo"));
		
//		指定分词器
		Analyzer analyzer = new IKAnalyzer();
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		
//		2、创建写入索引的对象
		indexWriter = new IndexWriter(directory, config);
	}
	
	
	
	/**
	 * 删除所有
	 * @throws Exception
	 */
	@Test
	public void deleteAllIndex() throws Exception{

		indexWriter.deleteAll();//慎用
		
		indexWriter.close();
	}
	
	/**
	 * 有条件的删除
	 * @throws Exception
	 */
	@Test
	public void deleteIndexByQuery() throws Exception{

//		indexWriter.deleteDocuments(new TermQuery(new Term("name", "spring")));
		indexWriter.deleteDocuments(new Term("name", "spring"));
		
		indexWriter.close();
	}

	@Test
	public void addIndex() throws Exception {
		
//		3、获取源文档
		File srcFile = new File("D:\\ITCAST\\Lucene&solr\\Lucene&solr-day01\\资料\\上课用的查询资料searchsource");
		File[] listFiles = srcFile.listFiles();
		for (File file : listFiles) {
			Document doc = new Document();
//		     1、文件名称
			String fileName = file.getName();
			Field nameField = new TextField("name", fileName, Store.YES);
//			new StringField(name, value, stored)
			doc.add(nameField);
//		     2、文件大小
			long fileSize = FileUtils.sizeOf(file);
//			区间  数值型
			Field sizeField = new LongField("size", fileSize, Store.YES);
			doc.add(sizeField);
//		     3、文件路径
			String filePath = file.getPath();
			Field pathField = new StoredField("path", filePath);
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
	
	/**
	 * 索引库修改
	 * 替换本质：先删除后添加
	 * @throws Exception 
	 */
	@Test
	public void updateIndex() throws Exception{
		Term term = new Term("name", "spring");
		
		Document doc = new Document();
		TextField textField = new TextField("name", "新文档新indexWriter新文档新文档新文updateDocument新文档新文档新文档新文档apache", Store.YES);
		textField.setBoost(10);
		doc.add(textField);
		doc.add(new TextField("content", "新文档内容", Store.YES));
		
		indexWriter.updateDocument(term, doc);
		
		indexWriter.close();
	}
	
	@Test
	public void searchIndex() throws Exception{
//		1、指定索引库位置
		Directory directory = FSDirectory.open(new File("D:\\class297\\indexRepo"));
		
		IndexReader  indexReader = DirectoryReader.open(directory);
		
		IndexSearcher  indexSearcher = new IndexSearcher(indexReader);
		
		
		Query query = new TermQuery(new Term("name", "apache"));
		
//		Query query = new MatchAllDocsQuery();  //查询所有
		
//		Query query = NumericRangeQuery.newLongRange("size", 100l, 1000l, true, true);
		
//		BooleanQuery  query = new BooleanQuery();
//		Query query1 = new TermQuery(new Term("name", "apache"));
//		Query query2 = new TermQuery(new Term("content", "spring"));
//		query.add(query1, Occur.SHOULD);
//		query.add(query2, Occur.SHOULD);
		
//		QueryParser queryParser = new QueryParser("name", new IKAnalyzer());
//		QueryParser queryParser = new MultiFieldQueryParser(new String[]{"name","content"}, new IKAnalyzer());
		
//		Query query = queryParser.parse("lucene is a project of apache");
//		(name:lucene content:lucene) (name:project content:project) (name:apache content:apache)

		
//		name:lucene name:project name:apache
		
		System.out.println(query);
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		
		
		System.out.println("总记录数："+topDocs.totalHits);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;
			Document doc = indexSearcher.doc(docID);
			System.out.println(doc.get("name"));
			System.out.println(doc.get("size"));
			System.out.println(doc.get("path"));
//			System.out.println(doc.get("content"));
		}
		indexReader.close();
	}

}
