package edu.epam.web.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class InfoTimeTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(InfoTimeTag.class);
    private String language;


    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        ResourceBundle bundle = ResourceBundle.getBundle(ResourceBundleKey.RESOURCE_BUNDLE_NAME, Locale.forLanguageTag(language.toUpperCase()));
        String headerDateTime = bundle.getString(ResourceBundleKey.DATE_TIME_KEY);
        String time = "";
        if (language != null && !language.isEmpty()) {
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL,
                    Locale.forLanguageTag(language.toUpperCase()));
            time = "<hr/>" + headerDateTime + "<b>" + dateFormat.format(gc.getTime()) + "</b><hr/>";
        }
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            logger.error(e);
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
