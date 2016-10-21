/**
 * 
 */
package org.vsg.rmodel.schejob.search.image;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import net.semanticmetadata.lire.indexers.parallel.ParallelIndexer;

/**
 * @author ruanweibiao
 *
 */
public class StandarSearchIndex implements SearchIndex {
	
	
	private File imageIndexFolder;
	
	@Inject
	public StandarSearchIndex(@Named("images.index.folder") File imageIndexFolder) {
		this.imageIndexFolder = imageIndexFolder;
	}
	

	/* (non-Javadoc)
	 * @see org.vsg.rmodel.schejob.search.image.SearchIndex#indexData()
	 */
	@Override
	public void indexData() {
        // use ParallelIndexer to index all photos from args[0] into "index" ... use 6 threads (actually 7 with the I/O thread).
        ParallelIndexer indexer = new ParallelIndexer(6, "index", imageIndexFolder.getPath());
        // use this to add you preferred builders. For now we go for CEDD, FCTH and AutoColorCorrelogram
        indexer.addExtractor(CEDD.class);
        indexer.addExtractor(FCTH.class);
        indexer.addExtractor(AutoColorCorrelogram.class);
        indexer.run();

	}

}
