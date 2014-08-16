
package net.opencms.service;

import java.awt.image.BufferedImage;

import net.opencms.Setting.CaptchaType;

public interface CaptchaService {

	BufferedImage buildImage(String captchaId);

	boolean isValid(CaptchaType captchaType, String captchaId, String captcha);

}