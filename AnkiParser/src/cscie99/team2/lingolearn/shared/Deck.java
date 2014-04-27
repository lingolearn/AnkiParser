package cscie99.team2.lingolearn.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import cscie99.team2.lingolearn.shared.error.CardNotFoundException;

/**
 * This class represents a deck of the flashcards.
 */
public class Deck implements Serializable {
	// The id to use for the serialization version
	private static final long serialVersionUID = -6277584849217764742L;

	// The unique id of the deck
	private Long id;
	
	// The map of cards that are part of this deck
	private HashMap<Long, Card> cards;
	
	private HashSet<Card> cardSet;
	
	private ArrayList<Card> cardList;
	
	// The ids of the cards that are part of this deck
	private List<Long> cardIds;
		
	// The language of this deck
	private String language;
	
	// The native language of the translations
	private String nativeLangauge;
	
	private String name;
	/**
	 * Constructor.
	 */
	public Deck(Long id, List<Card> cards, String language, String nativeLanguage) {
		this.id = id;
		this.language = language;
		this.nativeLangauge = nativeLanguage;
		this.cards = new HashMap<Long, Card>();
		this.cardIds = new ArrayList<Long>();
		this.cardSet = new HashSet<Card>();
		this.cardList = new ArrayList<Card>();
		
		for (Card card : cards) {
			this.add(card);
		}
	}
	
	public Deck() {
		
		this.cards = new HashMap<Long, Card>();
		this.cardIds = new ArrayList<Long>();
		this.cardSet = new HashSet<Card>();
		this.cardList = new ArrayList<Card>();
	}
	
	/**
	 * Add the card indicated to the deck. If it already exists then this 
	 * operation will be ignored.
	 */
	public void add(Card card) {
		if (cardSet.contains(card)) {
			return;
		}
		cardIds.add(card.getId());
		//cards.put(card.getId(), card);
		cardSet.add(card);
		cardList.add(card);
	}
	
	/**
	 * Get the card with the id indicated.
	 */
	public Card getCard(Long id) throws CardNotFoundException {
		// Is this card part of the deck?
		if (!cardIds.contains(id)) {
			throw new CardNotFoundException("The card with the id " + id + " is not part of this deck.");
		}
		// Do we already have the card in the local cache?
		if (cards.containsKey(id)) {
			return cards.get(id);
		}
		// Get the card and make sure we keep a local copy

		// TODO Get the card from the data layer
		throw new CardNotFoundException("TODO Get the card");
	}
	
	/**
	 * Get the id of the cards that are part of this deck.
	 */
	public List<Long> getCardIds() {
		return cardIds;
	}
	
	/**
	 * Get the id of this deck.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Get the language of this deck.
	 */
	public String getLangauge() {
		return language;
	}
	
	/**
	 * Get the native language of this deck.
	 */
	public String getNativeLangauge() {
		return nativeLangauge;
	}
	
	/**
	 * Get the size of the deck.
	 */
	public int getSize() {
		return cardIds.size();
	}
	
	/**
	 * Remove the card from the deck. If it is not in the deck then this
	 * operation will be ignored.
	 */
	public void remove(Card card) {
		if (!cardIds.contains(card.getId())) {
			return;
		}
		// Remove the card id from the list of ids
		for (int ndx = 0; ndx < cardIds.size(); ndx++) {
			if (cardIds.get(ndx) == card.getId()) {
				cardIds.remove(ndx);
				break;
			}
		}
		cards.remove(card.getId());
	}
	
	/**
	 * Set the language of this deck.
	 */
	public void setLangauge(String language) {
		this.language = language;
	}
	
	/**
	 * Set the native language of this deck.
	 */
	public void setNativeLangauge(String nativeLanguage) {
		this.nativeLangauge = nativeLanguage;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCards(HashMap<Long, Card> cards) {
		this.cards = cards;
	}

	public void setCardIds(List<Long> cardIds) {
		this.cardIds = cardIds;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public HashSet<Card> getCardSet() {
		return cardSet;
	}

	public void setCardSet(HashSet<Card> cardSet) {
		this.cardSet = cardSet;
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardIds == null) ? 0 : cardIds.hashCode());
		result = prime * result
				+ ((cardList == null) ? 0 : cardList.hashCode());
		result = prime * result + ((cardSet == null) ? 0 : cardSet.hashCode());
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((nativeLangauge == null) ? 0 : nativeLangauge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		if (cardIds == null) {
			if (other.cardIds != null)
				return false;
		} else if (!cardIds.equals(other.cardIds))
			return false;
		if (cardList == null) {
			if (other.cardList != null)
				return false;
		} else if (!cardList.equals(other.cardList))
			return false;
		if (cardSet == null) {
			if (other.cardSet != null)
				return false;
		} else if (!cardSet.equals(other.cardSet))
			return false;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (nativeLangauge == null) {
			if (other.nativeLangauge != null)
				return false;
		} else if (!nativeLangauge.equals(other.nativeLangauge))
			return false;
		return true;
	}

	
	
}
