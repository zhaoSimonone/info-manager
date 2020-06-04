package school.info.manage.infomanager.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用来传递分页信息，从service返回数据到前端页面的时候，不仅需要传送分页以后的数据，还需要传送当前的也页面
 */
@Data
@Slf4j
public class PageDTO {
  /**
   * 当前页查询到的所有info
   */
  private List<InfoDTO> infoDTOList;

  //返回到上一页
  private boolean showPrevious;

  //返回到第一页
  private boolean showFirstPage;

  //返回到下一页
  private boolean showNext;

  //返回到最后一页
  private boolean showEndPage;

  private Integer firstPage;
  private Integer endPage;
  private Integer previousPage;
  private Integer nextPage;

  private Integer currentPage;
  private List<Integer> pages = new ArrayList<>();

  /**
   * @param totalCount 总的记录的数量
   * @param currPage
   * @param size
   */
  public void setPageValues(Integer totalCount, Integer currPage, Integer size) {
    //总的分页的数量
    Integer totalPage;
    //计算
    if (totalCount * 2 == 0) {
      totalPage = totalCount / size;
    } else {
      totalPage = totalCount / size + 1;
    }
    //TODO 进行容错处理，防止请求中的page不合法
    if (currPage < 1) {
      currPage = 1;
    } else if (currPage > totalPage) {
      currPage = totalPage;
    }
    this.currentPage = currPage;

    //设置上一页，下一页
    this.firstPage = 1;
    this.endPage = totalPage;
    this.previousPage = this.currentPage - 1;
    this.nextPage = this.currentPage + 1;

    pages.add(currPage);
    //展示当前页的前三页，和后三页
    for (int i = 1; i <= 3; ++i) {
      //一个是往头部插入，一个是往尾部插入，综合以后，能保证pages中的元素是有序的
      if (currPage - i > 0) {
        pages.add(currPage - i);
      }
      if (currPage + i <= totalPage) {
        pages.add(currPage + i);
      }
    }
    Collections.sort(pages);
    log.info("pages: " + pages.toString());
    log.info("size: " + size);

    //当currPage==1的时候，就没有前一页了，就不展示上一页的按键
    if (currPage == 1) {
      this.showPrevious = false;
    } else {
      this.showPrevious = true;
    }

    //当currPage==totalPage的时候则说明到了最后一页，就没有展示上一页的按键
    if (currPage.equals(totalPage)) {
      this.showNext = false;
    } else {
      this.showNext = true;
    }

    //是否展示回到首页
    //如果当前展示的页面中不包含第一页，则显示第一页，否则不显示
    if (!pages.contains(1)) {
      this.showFirstPage = true;
    } else {
      this.showFirstPage = false;
    }

    //如果当前展示的页面中不包含最后页，则显示最后一页，否则不显示
    if (!pages.contains(totalPage)) {
      this.showEndPage = true;
    } else {
      this.showEndPage = false;
    }
  }

  /**
   * 当数据库中没有对应的数据的时候，则一条也不显示
   */
  public void setPageWhileCountEqualsZero(){
    pages.add(1);
    //返回到上一页
    showPrevious = false;
    //返回到第一页
    showFirstPage = false;
    //返回到下一页
    showNext = false;
    //返回到最后一页
    showEndPage = false;
  }
}
