package com.Config;

//Это тоже рабочий конфиг, но вместо него добавил другой кофиг - AppInit
public class WebServletConfiguration /* implements WebApplicationInitializer */ {
 /*
 public void onStartup(ServletContext ctx) throws ServletException {

  System.out.println("this is WebServletConfiguration");
  AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
  System.out.println("webCtx:  "+webCtx);
  webCtx.register(SpringConfig.class);
  webCtx.setServletContext(ctx);
  ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webCtx));
  servlet.setLoadOnStartup(1);
  servlet.addMapping("/");
 }
 */
}