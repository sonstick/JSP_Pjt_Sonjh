package vo;
import java.sql.Timestamp;

public class ProductVO {
	private int p_no;
    private String country;
    private String city;
	private String p_name;
    private String p_content;
    private String p_thumbs;
    private String p_image_1;
    private String p_image_2;
    private String p_image_3;
    private String p_image_4;
    private int p_readCnt;
    private Timestamp p_reg_date;
    
    public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_thumbs() {
		return p_thumbs;
	}
	public void setP_thumbs(String p_thumbs) {
		this.p_thumbs = p_thumbs;
	}
	public String getP_image_1() {
		return p_image_1;
	}
	public void setP_image_1(String p_image_1) {
		this.p_image_1 = p_image_1;
	}
	public String getP_image_2() {
		return p_image_2;
	}
	public void setP_image_2(String p_image_2) {
		this.p_image_2 = p_image_2;
	}
	public String getP_image_3() {
		return p_image_3;
	}
	public void setP_image_3(String p_image_3) {
		this.p_image_3 = p_image_3;
	}
	public String getP_image_4() {
		return p_image_4;
	}
	public void setP_image_4(String p_image_4) {
		this.p_image_4 = p_image_4;
	}
	public int getP_readCnt() {
		return p_readCnt;
	}
	public void setP_readCnt(int p_readCnt) {
		this.p_readCnt = p_readCnt;
	}
	public Timestamp getP_reg_date() {
		return p_reg_date;
	}
	public void setP_reg_date(Timestamp p_reg_date) {
		this.p_reg_date = p_reg_date;
	}
}
