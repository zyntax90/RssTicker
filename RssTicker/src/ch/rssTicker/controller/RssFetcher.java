package ch.rssTicker.controller;

import java.util.Collections;
import java.util.Map.Entry;


import ch.common.utils.Mail;
import ch.common.utils.MailUtil;
import ch.common.utils.RssChannel;
import ch.common.utils.RssItem;
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
					Object content = createContentForMail(channel);
					sendMail(content);
				}
			});

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		Thread.currentThread().interrupt();
	}

	private Object createContentForMail(RssChannel channel) {
		StringBuffer stringBuffer = new StringBuffer();
		for (RssItem rssItem : channel.getRssItems()) {
			stringBuffer.append(String.format("%s: %s \n", RssItem.TITLE, rssItem.getTitle()));
			stringBuffer.append(String.format("%s: %s \n", RssItem.PUBDATE, rssItem.getPubDate()));
			stringBuffer.append(String.format("%s: %s \n", RssItem.DESCR, rssItem.getDescription()));
			stringBuffer.append(String.format("%s: %s \n", RssItem.LINK, rssItem.getLink()));
			stringBuffer.append("\n");
			for (Entry<String, String> otherValue : rssItem.getOtherValues().entrySet()) {
				stringBuffer.append(String.format("%s: %s\n", otherValue.getKey(), otherValue.getValue()));
			}
			
			stringBuffer.append(String.join("", Collections.nCopies(100, "-")));
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
	}

	private void sendMail(Object content) {
		MailUtil mailUtil = new MailUtil("snoopyflopp@gmail.com", "HakunaMatata");
		mailUtil.sendMail("smtp.googlemail.com",
				new Mail("RssFeedDownloader", "roshan90@hispeed.ch", "RSSFeed-Anime", content));
	}

}
