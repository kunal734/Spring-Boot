package org.jsp.shoppingkartapi.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.shoppingkartapi.model.Merchant;
import org.jsp.shoppingkartapi.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	public boolean deleteById(int id) {
		Optional<Merchant> recMerchant = findById(id);
		if (recMerchant.isPresent()) {
			merchantRepository.delete(recMerchant.get());
			return true;
		}
		return false;
	}

	public Optional<Merchant> verifyMerchantByPhone(long phone, String password) {
		return merchantRepository.verifyByPhone(phone, password);
	}

	public Optional<Merchant> verifyMerchantById(int id, String password) {
		return merchantRepository.verifyById(id, password);
	}

	public Optional<Merchant> verifyMerchantByEmail(String email, String password) {
		return merchantRepository.verifyByEmail(email, password);
	}

	public Optional<Merchant> verifyMerchantByGst(String gst, String password) {
		return merchantRepository.verifyByGst(gst, password);
	}
}
