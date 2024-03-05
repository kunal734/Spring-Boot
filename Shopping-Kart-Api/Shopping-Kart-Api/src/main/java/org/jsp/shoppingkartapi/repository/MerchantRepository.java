package org.jsp.shoppingkartapi.repository;

import java.util.Optional;

import org.jsp.shoppingkartapi.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	public Optional<Merchant> verifyByEmail(String email, String password);

	@Query("select m from Merchant m where m.id=?1 and m.password=?2")
	public Optional<Merchant> verifyById(int id, String password);

	@Query("select m from Merchant m where m.gst=?1 and m.password=?2")
	public Optional<Merchant> verifyByGst(String gst, String password);

	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	public Optional<Merchant> verifyByPhone(long phone, String password);
}
