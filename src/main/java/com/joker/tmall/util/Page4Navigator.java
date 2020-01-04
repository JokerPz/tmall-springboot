package com.joker.tmall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator <T> {
    /**
     * jap的page类
     */
    Page<T> pageFromJpa;

    /**
     * 导航页数目 如 1 2 3 4 5 6 7 的数目是7
     */
    int navigatePages;

    /**
     * 总页码
     */
    int totalPages;

    /**
     *
     */
    int number;

    /**
     *
     */
    Long totalElements;

    /**
     *
     */
    int size;

    /**
     *
     */
    int numberOfElements;

    /**
     *
     */
    List<T> content;

    /**
     *
     */
    boolean isHasContent;

    /**
     *
     */
    boolean first;

    /**
     *
     */
    boolean last;

    /**
     *
     */
    boolean isHasNext;

    /**
     *
     */
    boolean isHasPrevous;

    /**
     *
     */
    int[] navigatepageNums;

    public Page4Navigator() {

    }

    public Page4Navigator(Page<T> pageFromJpa, int navigatePages) {
        this.pageFromJpa = pageFromJpa;
        this.navigatePages = navigatePages;
        totalPages = pageFromJpa.getTotalPages();
        totalPages = pageFromJpa.getTotalPages();
        number = pageFromJpa.getNumber();
        totalElements = pageFromJpa.getTotalElements();
        size = pageFromJpa.getSize();
        numberOfElements = pageFromJpa.getNumberOfElements();
        content = pageFromJpa.getContent();
        isHasContent = pageFromJpa.hasContent();
        first = pageFromJpa.isFirst();
        last = pageFromJpa.isLast();
        isHasNext = pageFromJpa.hasNext();
        isHasPrevous = pageFromJpa.hasPrevious();
        calcNavigatepageNums();
    }


    private void calcNavigatepageNums() {
        int[] navigatepageNums;
        // 首页
        int firstPage = 1;
        if (totalPages < navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 1; i <= totalPages; i++) {
                navigatepageNums[i] = i++;
            }
        } else {
            navigatepageNums = new int[navigatePages];
            int startNo = number - navigatePages/2;
            int endNo = number + navigatePages/2;
            if (startNo < firstPage) {
                startNo = firstPage;
                for (int i = 0; i < navigatePages; i++, startNo++) {
                    navigatepageNums[i] = startNo;
                }
            } else if (endNo > totalPages) {
                endNo = totalPages;
                for (int i = navigatePages - 1; i >= 0; i++, endNo-- ) {
                    navigatepageNums[i] = endNo;
                }
            } else {
                for (int i = 0; i < navigatePages; i++, startNo++) {
                    navigatepageNums[i] = startNo;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }

    public Page<T> getPageFromJpa() {
        return pageFromJpa;
    }

    public void setPageFromJpa(Page<T> pageFromJpa) {
        this.pageFromJpa = pageFromJpa;
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

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
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

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevous() {
        return isHasPrevous;
    }

    public void setHasPrevous(boolean hasPrevous) {
        isHasPrevous = hasPrevous;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
