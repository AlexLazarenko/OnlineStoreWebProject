package edu.epam.web.command;

/**
 * The type Router.
 */
public class Router {

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Forward type.
         */
        FORWARD,
        /**
         * Redirect type.
         */
        REDIRECT
    }

    private String pagePath;
    private Type type;

    /**
     * Instantiates a new Router.
     */
    public Router() {
        this.type = Type.FORWARD;
    }

    /**
     * Instantiates a new Router.
     *
     * @param pagePath the page path
     */
    public Router(String pagePath) {
        this(pagePath, Type.FORWARD);
    }

    /**
     * Instantiates a new Router.
     *
     * @param pagePath the page path
     * @param type     the type
     */
    public Router(String pagePath, Type type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets page path.
     *
     * @param pagePath the page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }
}
