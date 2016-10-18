package org.vsg.rmodel.schejob.search.image;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class JobRunModule extends AbstractModule {

	@Override
	protected void configure() {
		
		this.bind(File.class).annotatedWith(Names.named("images.index.folder")).toInstance(new File("f://image_files"));
		
		
		this.bind(SearchIndex.class).to( StandarSearchIndex.class );
		

	}

}
