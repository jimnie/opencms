
package net.opencms.service;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Member;
import net.opencms.entity.Message;

public interface MessageService extends BaseService<Message, Long> {

	Page<Message> findPage(Member member, Pageable pageable);

	Page<Message> findDraftPage(Member sender, Pageable pageable);

	Long count(Member member, Boolean read);

	void delete(Long id, Member member);

}