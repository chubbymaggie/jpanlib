package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fileWalker.SourceFileWalker;
import fileWalker.UnorderedWalker;

public class CodebaseWalkerTest
{

	@Test
	public void testRecursiveDirSearch()
	{
		String[] args = { "src/tests/languages/c/samples/" };

		SourceFileWalker provider = new UnorderedWalker();

		try
		{
			Set<String> expected = new HashSet<String>();
			expected.add("src/tests/languages/c/samples/subdir/test.c");
			expected.add("src/tests/languages/c/samples/test.c");
			expected.add("src/tests/languages/c/samples/tiff.cpp");

			FilenameAggregator listener = new FilenameAggregator();
			provider.addListener(listener);
			provider.walk(args);

			assertEquals(expected, new HashSet<String>(listener.filenames));
		} catch (IOException e)
		{
			fail("IO Error");
		}
	}

}
