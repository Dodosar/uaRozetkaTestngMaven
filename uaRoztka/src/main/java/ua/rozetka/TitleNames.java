package ua.rozetka;



public enum TitleNames {
	MAINPAGE("Интернет-магазин ROZETKA™: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие"),
    
	SMARTPAGE("Смартфоны, ТВ и электроника - Rozetka.ua | Купить Смартфоны, ТВ и электроника в Киеве: цена, отзывы, продажа"),
	
	TELEPHONE("Телефоны - Rozetka.ua | Купить Телефоны в Киеве: цена, отзывы, продажа"),
	
	SMARTPHONE("Смартфоны - Rozetka.ua | Купить смартфон в Киеве: цена, отзывы, продажа");
	
	private final String text;

	    /**
	     * @param text
	     */
		private TitleNames(final String text) {
	        this.text = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return text;
	    }
}
