package ua.rozetka;



public enum TitleNames {
	MAINPAGE("��������-������� ROZETKA�: �����������, ������������, ������������, ���������� � ������������ �������������"),
    
	SMARTPAGE("���������, �� � ����������� - Rozetka.ua | ������ ���������, �� � ����������� � �����: ����, ������, �������"),
	
	TELEPHONE("�������� - Rozetka.ua | ������ �������� � �����: ����, ������, �������"),
	
	SMARTPHONE("��������� - Rozetka.ua | ������ �������� � �����: ����, ������, �������");
	
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
