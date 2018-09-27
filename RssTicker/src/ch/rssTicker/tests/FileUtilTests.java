package ch.rssTicker.tests;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FileUtilTests {

	@Test
	void testUrlStreamToFile() {

		try (InputStream stream = new BufferedInputStream(
				new URL("https://nyaa.si/download/1079070.torrent").openStream())) {
			Files.copy(stream, Paths.get("testFile.torrent"));
			boolean isValid = Files.deleteIfExists(Paths.get("testFile.torrent"));

			assertTrue(isValid);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
