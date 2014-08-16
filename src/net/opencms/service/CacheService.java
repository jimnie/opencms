
package net.opencms.service;

public interface CacheService {

	String getDiskStorePath();

	int getCacheSize();

	void clear();

}