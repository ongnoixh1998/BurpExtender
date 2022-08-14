package burp;

import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender, IHttpListener {
	private IBurpExtenderCallbacks callbacks;
	private PrintWriter stdout;

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		// keep a reference to our callbacks object
		this.callbacks = callbacks;

		// set our extension name
		callbacks.setExtensionName("Event listeners");

		// obtain our output stream
		stdout = new PrintWriter(callbacks.getStdout(), true);

		// register ourselves as an HTTP listener
		callbacks.registerHttpListener(this);
	}
	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		stdout.println(
				(messageIsRequest ? "HTTP request to " : "HTTP response from ") +
						messageInfo.getHttpService() +
						" [" + callbacks.getToolName(toolFlag) + "]");
	}
}
