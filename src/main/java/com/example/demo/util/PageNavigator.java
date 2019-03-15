package com.example.demo.util;

import java.util.List;

import org.springframework.data.domain.Page;


public class PageNavigator<T> {
	
	int navigatePages;
    
    int totalPages;
    
    List<T> content;
  
    int number;
      
    long totalElements;   
  
    int numberOfElements;
  
    boolean isHasContent;
  
    boolean first;
  
    boolean last;
      
    boolean isHasNext;
  
    boolean isHasPrevious;
      
    int[] navigatepageNums;

	public PageNavigator(Page<T> pageFromJPA,int navigatePages){
	    this.navigatePages = navigatePages;
	    first = pageFromJPA.isFirst();
	    last = pageFromJPA.isLast();
		number = pageFromJPA.getNumber();
		totalPages = pageFromJPA.getTotalPages();
		totalElements = pageFromJPA.getTotalElements();	
		numberOfElements = pageFromJPA.getNumberOfElements();
		isHasContent = pageFromJPA.hasContent();
		isHasNext = pageFromJPA.hasNext();
		isHasPrevious  = pageFromJPA.hasPrevious(); 
		content = pageFromJPA.getContent();
		calcNavigatepageNums();
	}
	
	private void calcNavigatepageNums() {  
        //当总页数小于或等于导航页码数时
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = number - navigatePages / 2;
            int endNum = number + navigatePages / 2;
  
            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {
                endNum = totalPages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }    
    }

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean isHasContent() {
		return isHasContent;
	}

	public void setHasContent(boolean isHasContent) {
		this.isHasContent = isHasContent;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isHasNext() {
		return isHasNext;
	}

	public void setHasNext(boolean isHasNext) {
		this.isHasNext = isHasNext;
	}

	public boolean isHasPrevious() {
		return isHasPrevious;
	}

	public void setHasPrevious(boolean isHasPrevious) {
		this.isHasPrevious = isHasPrevious;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	
	
	
}
