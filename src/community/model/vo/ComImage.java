package community.model.vo;

public class ComImage {

	private int imgnum;
	private int boardnum;
	private String originalname;
	private String renamename;
	public ComImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComImage(int imgnum, int boardnum, String originalname, String renamename) {
		super();
		this.imgnum = imgnum;
		this.boardnum = boardnum;
		this.originalname = originalname;
		this.renamename = renamename;
	}
	@Override
	public String toString() {
		return "ComImage [imgnum=" + imgnum + ", boardnum=" + boardnum + ", originalname=" + originalname
				+ ", renamename=" + renamename + "]";
	}
	public int getImgnum() {
		return imgnum;
	}
	public void setImgnum(int imgnum) {
		this.imgnum = imgnum;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getOriginalname() {
		return originalname;
	}
	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}
	public String getRenamename() {
		return renamename;
	}
	public void setRenamename(String renamename) {
		this.renamename = renamename;
	}
	
	
}
