package ua.rozetka;



public enum Elements {
		
    PRODUCT_NAME("product name", "//div[@class='g-i-tile-i-box-desc'][.//*[@class='g-tag g-tag-icon-middle-popularity sprite']]//*[@class='g-i-tile-i-title clearfix']/a"),
    PRODUCT_PRICE("product price", "//div[@class='g-i-tile-i-box-desc'][.//*[@class='g-tag g-tag-icon-middle-popularity sprite']]//*[@class='g-price g-price-cheaper' or @class='g-price']"),

    PRODUCT_PAGINATION_1("page 1","//*[@id='page1']"),
    PRODUCT_PAGINATION_2("page 2","//*[@id='page2']"),
    PRODUCT_PAGINATION_3("page 3","//*[@id='page3']")
    ;

    private final String name;
    private final String xpath;

    private Elements(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }
    public String getXPath() {
        return xpath;
    }

	public static Elements getEntryForElementName(String elementName) {
		// TODO Auto-generated method stub
		for(Elements elemnts : values()){
			if(elemnts.name.equals(elementName)){
				return elemnts;
			}
		}
		throw new RuntimeException("Cannot find entry for fieldName: '" + elementName + "'");
	}
}
