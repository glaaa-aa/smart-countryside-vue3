package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.CardMapper;
import com.qfy.modules.city3d.po.Card;
import com.qfy.modules.city3d.po.CardExample;
import com.qfy.modules.city3d.po.CardExample.Criteria;
import com.qfy.modules.city3d.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardMapper cardMapper;

	@Override
	public List<Card> getCardAll() {
		List<Card> result = cardMapper.selectByExample(null);
		return result;
	}

	@Override
	public Card getCardById(String crId) {
		Card result = cardMapper.selectByPrimaryKey(crId);
		return result;
	}

	@Override
	public List<Card> getCardByCard(Card card) {
		CardExample example = new CardExample();
		Criteria criteriaResult = example.or();
		if(card.getCrCardnum()!=null) {criteriaResult=criteriaResult.andCrCardnumEqualTo(card.getCrCardnum());}
		List<Card> result = cardMapper.selectByExample(example);
		return result;
	}

	@Override
	public int insert(Card card) {
		int result = cardMapper.insert(card);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Card card) {
		int result = cardMapper.updateByPrimaryKey(card);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String crId) {
		int result = cardMapper.deleteByPrimaryKey(crId);
		return result;
	}
}
