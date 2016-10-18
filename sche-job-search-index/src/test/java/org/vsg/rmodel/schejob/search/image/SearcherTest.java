/**
 * 
 */
package org.vsg.rmodel.schejob.search.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;

/**
 * @author ruanweibiao
 *
 */
public class SearcherTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
        BufferedImage img = null;
        File f = new File("e://test_file/test.jpg");
        try {
            img = ImageIO.read(f);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
        ImageSearcher searcher = new GenericFastImageSearcher(30, CEDD.class);

        // searching with a image file ...
        ImageSearchHits hits = searcher.search(img, ir);
        // searching with a Lucene document instance ...
        for (int i = 0; i < hits.length(); i++) {
            String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            System.out.println(hits.score(i) + ": \t" + fileName);
        }
	}

}
