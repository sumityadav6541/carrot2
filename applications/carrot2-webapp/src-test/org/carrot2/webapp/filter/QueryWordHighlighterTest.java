package org.carrot2.webapp.filter;

import static org.fest.assertions.Assertions.assertThat;

import org.carrot2.core.Document;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Test cases for {@link QueryWordHighlighter}.
 */
public class QueryWordHighlighterTest
{
    @Test
    public void testNullQuery()
    {
        check(null, "test", null);
        check("", "test", null);
    }

    @Test
    public void testOneWordQuery()
    {
        check("test", "this is a test", "this is a <b>test</b>");
    }

    @Test
    public void testMultiWordQuery()
    {
        check("some test case", "many tests will fail in some case",
            "many <b>test</b>s will fail in <b>some</b> <b>case</b>");
    }
    
    @Test
    public void testCaseInsensitivity()
    {
        check("tEst", "test TEST tEst Test", "<b>test</b> <b>TEST</b> <b>tEst</b> <b>Test</b>");
    }

    @Test
    public void testSpecialCharactersQuery()
    {
        check("x23+?.", "x23+?.g zz x23", "<b>x23+?.</b>g zz x23");
    }
    
    private void check(String query, String snippetToHighlight, String expectedSnippet)
    {
        final Document document = new Document();
        document.addField(Document.SUMMARY, snippetToHighlight);

        final QueryWordHighlighter highlighter = new QueryWordHighlighter();
        highlighter.documents = Lists.newArrayList(document);
        highlighter.query = query;

        highlighter.process();

        assertThat(
            document.getField(Document.SUMMARY
                + QueryWordHighlighter.HIGHLIGHTED_FIELD_NAME_SUFFIX)).isEqualTo(
            expectedSnippet);
    }
}
