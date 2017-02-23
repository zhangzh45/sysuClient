package Myfilter;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter extends StrutsPrepareAndExecuteFilter{         //修改struts2 默认的过滤器
    
     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURI().contains("ServerInterfacePort"));
            if(request.getRequestURI().contains("ServerInterfacePort"))              //如果是访问webservice 这不进行过滤
            {
             chain.doFilter(req,res);
            }
            else{
            try {
                prepare.setEncodingAndLocale(request, response);
                prepare.createActionContext(request, response);
                prepare.assignDispatcherToThread();
                if ( excludedPatterns != null && prepare.isUrlExcluded(request, excludedPatterns)) {
                    chain.doFilter(request, response);
                } else {
                    request = prepare.wrapRequest(request);
                    ActionMapping mapping = prepare.findActionMapping(request, response, true);
                    if (mapping == null) {
                        boolean handled = execute.executeStaticResourceRequest(request, response);
                        if (!handled) {
                            chain.doFilter(request, response);
                        }
                    } else {
                        execute.executeAction(request, response, mapping);
                    }
                }
            } finally {
                prepare.cleanupRequest(request);
            }
        }
     }

    
}