package cn.itcast.lucene;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexReaderTest {

	public static void main(String[] args) throws Exception {
		// 1、指定索引库的位置
		Directory directory = FSDirectory.open(new File("D:\\class297\\indexRepo"));

		// 2、创建读取索引对象
		IndexReader indexReader = DirectoryReader.open(directory);

		// 3、创建查询索引对象
		IndexSearcher searcher = new IndexSearcher(indexReader);

		// 4、执行查询方法 query:指定条件, n：查询数据量的限制
		// 5、获取查询结果
		// 这里的条件表示在域名等于content域查询spring
		Query query = new TermQuery(new Term("content", "spring"));
		TopDocs topDocs = searcher.search(query, 100);

		System.out.println("总记录数：" + topDocs.totalHits);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;
			Document doc = searcher.doc(docID);
			// 获取文档的内容
			System.out.println("文件名：" + doc.get("name"));
			System.out.println("文件大小：" + doc.get("size"));
			System.out.println("文件路径：" + doc.get("path"));
			// System.out.println("文件内容："+doc.get("content"));

			System.out.println("************************************************************************");
			System.out.println("************************************************************************");

		}

		// 6、关闭资源
		indexReader.close();
	}

}
