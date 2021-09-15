package edu.epam.web.controller.filter;

import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.command.SessionAttribute;
import edu.epam.web.model.entity.User;
import edu.epam.web.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class RoleAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);

        String commandName = request.getParameter(RequestParameter.ACTION);

        if (commandName == null) {
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
            return;
        }

        UserRole role = UserRole.GUEST;
        Set<String> commandsByRole;

        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user != null) {
            role = user.getRole();
        }
        switch (role) {
            case CLIENT -> {
                commandsByRole = RoleAccessPermission.CLIENT.getCommands();
            }
            case STAFF -> {
                commandsByRole = RoleAccessPermission.STAFF.getCommands();
            }
            case ADMIN -> {
                commandsByRole = RoleAccessPermission.ADMIN.getCommands();
            }
            default -> {
                commandsByRole = RoleAccessPermission.GUEST.getCommands();
            }
        }
        if (!commandsByRole.contains(commandName.toUpperCase())) {
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
        } else
            // pass the request along the filter chain
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
