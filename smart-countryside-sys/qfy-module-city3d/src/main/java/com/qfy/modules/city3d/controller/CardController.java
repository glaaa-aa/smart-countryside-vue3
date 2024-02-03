package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.po.Card;
import com.qfy.modules.city3d.service.CardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/card")
public class CardController extends CrossOriginController {
	@Autowired
	private CardService cardService;

	@RequestMapping("/getCardAll")
	public @ResponseBody List<Card> getCardAll() throws Exception {
		List<Card> result = cardService.getCardAll();
		return result;
	}

	@RequestMapping("/getCardById")
	public @ResponseBody Card getCardById(String crId) throws Exception {
		Card result = cardService.getCardById(crId);
		return result;
	}

	@RequestMapping("/getCardByCard")
	public @ResponseBody List<Card> getCardByCard(@RequestBody Card card) throws Exception {
		List<Card> result = cardService.getCardByCard(card);
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody Card card) throws Exception {
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		card.setCrId(uid);
		int result = cardService.insert(card);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody Card card) throws Exception {
		int result = cardService.updateByPrimaryKey(card);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(String crId) throws Exception {
		int result = cardService.deleteByPrimaryKey(crId);
		return result;
	}
}
