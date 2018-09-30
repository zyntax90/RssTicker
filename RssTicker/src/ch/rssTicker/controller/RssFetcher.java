package ch.rssTicker.controller;

import ch.common.utils.RssChannel;
import ch.common.utils.RssUtil;
import javafx.application.Platform;

public class RssFetcher implements Runnable {

	private RssUtil rssUtil;

	public RssFetcher(String url) {
		rssUtil = new RssUtil(url);
	}

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// Do something
					RssChannel channel = rssUtil.getObjectFromRssFeed();
					System.out.println(channel.getRssItems());
				}
			});

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		Thread.currentThread().interrupt();
	}
	
}
