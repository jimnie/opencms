
package net.opencms.service;

import java.util.Map;

import net.opencms.entity.ProductNotify;
import net.opencms.entity.SafeKey;

public interface MailService {

	void send(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail, String subject, String templatePath, Map<String, Object> model, boolean async);

	void send(String toMail, String subject, String templatePath, Map<String, Object> model, boolean async);

	void send(String toMail, String subject, String templatePath, Map<String, Object> model);

	void send(String toMail, String subject, String templatePath);

	void sendTestMail(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail);

	void sendFindPasswordMail(String toMail, String username, SafeKey safeKey);

	void sendProductNotifyMail(ProductNotify productNotify);

}