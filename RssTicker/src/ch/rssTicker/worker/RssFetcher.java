package ch.rssTicker.worker;

import java.util.Collections;
import java.util.TimerTask;
import java.util.Map.Entry;

import ch.common.utils.Mail;
import ch.common.utils.MailUtil;
import ch.common.utils.RssChannel;
import ch.common.utils.RssItem;
import ch.common.utils.RssUtil;


public class RssFetcher extends TimerTask {

	private final RssUtil rssUtil;

	public RssFetcher(String url) {
		rssUtil = new RssUtil(url);
	}

	@Override
	public void run() {
		RssChannel channel = rssUtil.getObjectFromRssFeed();
		Object content = createContentForMail(channel);
		sendMail(content);
	}

	private Object createContentForMail(final RssChannel channel) {
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

	private void sendMail(final Object content) {
		MailUtil mailUtil = new MailUtil("snoopyflopp@gmail.com", "HakunaMatata");
		mailUtil.sendMail("smtp.googlemail.com",
				new Mail("RssFeedDownloader", "roshan90@hispeed.ch", "RSSFeed-Anime", content));
	}
}
