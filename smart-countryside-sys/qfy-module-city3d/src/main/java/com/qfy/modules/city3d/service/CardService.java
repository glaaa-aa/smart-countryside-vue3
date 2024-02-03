package com.qfy.modules.city3d.service;

import java.util.List;

import com.qfy.modules.city3d.po.Card;

public interface CardService {
	public List<Card> getCardAll();
	public Card getCardById(String crId);
	public List<Card> getCardByCard(Card card);
	public int insert(Card card);
	public int updateByPrimaryKey(Card card);
	public int deleteByPrimaryKey(String crId);
}
