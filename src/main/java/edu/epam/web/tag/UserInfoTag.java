package edu.epam.web.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserInfoTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(UserInfoTag.class);
    private String email;
    private String language;


    public void setLanguage(String language) {
        this.language = language;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle bundle = ResourceBundle.getBundle(ResourceBundleKey.RESOURCE_BUNDLE_NAME, Locale.forLanguageTag(language.toUpperCase()));
        String headerEmail=bundle.getString(ResourceBundleKey.EMAIL_KEY);
        try {
            if (email != null || !email.isEmpty()) {
                JspWriter out = pageContext.getOut();
                out.write("<hr/>"+headerEmail+"<b>" + email + "</b><hr/>");
            }
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
