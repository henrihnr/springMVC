package app.util;

import java.util.List;

public class SearchResult {

	private List recs;
	private PagingInfo pagingInfo;
	
	public SearchResult() {}
	public SearchResult(List recs, PagingInfo pagingInfo) {
		this.recs = recs;
		this.pagingInfo = pagingInfo;
	}
	
	public List getRecs() {
		return recs;
	}
	public void setRecs(List recs) {
		this.recs = recs;
	}
	
	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}
	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}
	
}
