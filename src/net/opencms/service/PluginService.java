
package net.opencms.service;

import java.util.List;

import net.opencms.plugin.PaymentPlugin;
import net.opencms.plugin.StoragePlugin;

public interface PluginService {

	List<PaymentPlugin> getPaymentPlugins();

	List<StoragePlugin> getStoragePlugins();

	List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);

	List<StoragePlugin> getStoragePlugins(boolean isEnabled);

	PaymentPlugin getPaymentPlugin(String id);

	StoragePlugin getStoragePlugin(String id);

}