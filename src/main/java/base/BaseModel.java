package base;

import com.github.pagehelper.PageInfo;
import constant.Constants;

public class BaseModel<T> {
	private String status = Constants.SUCCESS;
	private String message = "操作成功";
	private T data;
	private PageModel page;

	public PageModel getPage() {
		return page;
	}

	public void setPage(PageInfo<?> pageInfo) {
		this.page = new PageModel(pageInfo);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
