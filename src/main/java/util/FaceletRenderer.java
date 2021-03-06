package util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.view.ViewDeclarationLanguage;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;

/**
 * https://github.com/vvasabi/Programmatic-Facelets-Rendering-Example
 * @author byadriano
 *
 */
public class FaceletRenderer {
	 
	private FacesContext context;
 
	public FaceletRenderer(FacesContext context) {
		this.context = context;
	}
 
	/**
	 * Render the Facelets template specified.
	 *
	 * @param template path to the Facelets template
	 * @return rendered content of the Facelets template
	 */
	public String renderView(String template) {
		try {
			// store the original response writer
			ResponseWriter originalWriter = context.getResponseWriter();
 
			// put in a StringWriter to capture the output
			StringWriter stringWriter = new StringWriter();
			ResponseWriter writer = createResponseWriter(context, stringWriter);
			context.setResponseWriter(writer);
 
			// create a UIViewRoot instance using the template specified
			ViewHandler viewHandler = context.getApplication().getViewHandler();
			UIViewRoot view = viewHandler.createView(context, template);
 
			// the fun part -- do the actual rendering here
			ViewDeclarationLanguage vdl = viewHandler
					.getViewDeclarationLanguage(context, template);
			vdl.buildView(context, view);
			renderChildren(context, view);
 
			// restore the response writer
			if(originalWriter != null) {
				context.setResponseWriter(originalWriter);
			}
 
			return stringWriter.toString();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
 
	/**
	 * Create ResponseWriter. Taken from FaceletViewDeclarationLanguage.java of
	 * MyFaces.
	 */
	private ResponseWriter createResponseWriter(FacesContext context,
			Writer writer) {
		ExternalContext extContext = context.getExternalContext();
		Map<String, Object> requestMap = extContext.getRequestMap();
		String contentType = (String)requestMap.get("facelets.ContentType");
		String encoding = (String)requestMap.get("facelets.Encoding");
		RenderKit renderKit = context.getRenderKit();
		return renderKit.createResponseWriter(writer, contentType, encoding);
	}
 
	/**
	 * Render a UIComponent. Taken from JSF.java of Seam 2.2.
	 */
	private void renderChildren(FacesContext context, UIComponent component)
			throws IOException {
		List<UIComponent> children = component.getChildren();
		for (int i = 0, size = component.getChildCount(); i < size; i++) {
			UIComponent child = (UIComponent)children.get(i);
			renderChild(context, child);
		}
	}
 
	/**
	 * Render the child and all its children components.
	 */
	private void renderChild(FacesContext context, UIComponent child)
			throws IOException {
		if (child.isRendered()) {
			child.encodeAll(context);
		}
	}
 

	public void renderViewPDF(String template) {
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		String content = renderView(template);
		
		try {
			StringReader contentReader = new StringReader(content);
			InputSource source = new InputSource(contentReader);
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xhtmlContent = documentBuilder.parse(source);
			
			ITextRenderer iRenderer = new ITextRenderer();
			iRenderer.setDocument(xhtmlContent,"");
			iRenderer.layout();
			
			response.setContentType("application/pdf");
			OutputStream browserStream = response.getOutputStream();
			iRenderer.createPDF(browserStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
		context.responseComplete();
	}
	
}
