package cn.itcast.lucene;

import java.io.File;
import java.io.IOException;

import javax.swing.text.AttributeSet.CharacterAttribute;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzerTest {

	public static void main(String[] args) throws Exception {
		
//		Analyzer analyzer = new StandardAnalyzer(); //英文按照空格分词，中文一个字一个字
//		Analyzer analyzer = new CJKAnalyzer();  //中文两个字两个字
//		需要导入lucene-analyzers-smartcn-4.10.3.jar
//		Analyzer analyzer = new SmartChineseAnalyzer(); //中文还可以，但是英文容易出现缺字母
		
//		第三方分词器IK-Analyzer
//		需要导入jar IKAnalyzer2012FF_u1.jar
		Analyzer analyzer = new IKAnalyzer();
		
		
//		TokenStream tokenStream = analyzer.tokenStream(null, "The Spring Framework provides a comprehensive programming and configuration model.");
		TokenStream tokenStream = analyzer.tokenStream(null, "apache 全文检索是将整本书java、整篇文章中的任意内容信息查找出来的检索，java，此文出自传智播客，李诞");
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while(tokenStream.incrementToken()){
			System.out.println(charTermAttribute);
		}
	}

}
