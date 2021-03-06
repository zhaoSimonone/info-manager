package school.info.manage.infomanager.model;

public class Info {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.id
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.creator
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Integer creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.title
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.gmt_create
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.gmt_modified
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.comment_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.view_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.like_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.tag
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column info.description
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.id
     *
     * @return the value of info.id
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.id
     *
     * @param id the value for info.id
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.creator
     *
     * @return the value of info.creator
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.creator
     *
     * @param creator the value for info.creator
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.title
     *
     * @return the value of info.title
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.title
     *
     * @param title the value for info.title
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.gmt_create
     *
     * @return the value of info.gmt_create
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.gmt_create
     *
     * @param gmtCreate the value for info.gmt_create
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.gmt_modified
     *
     * @return the value of info.gmt_modified
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.gmt_modified
     *
     * @param gmtModified the value for info.gmt_modified
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.comment_count
     *
     * @return the value of info.comment_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.comment_count
     *
     * @param commentCount the value for info.comment_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.view_count
     *
     * @return the value of info.view_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.view_count
     *
     * @param viewCount the value for info.view_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.like_count
     *
     * @return the value of info.like_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.like_count
     *
     * @param likeCount the value for info.like_count
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.tag
     *
     * @return the value of info.tag
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.tag
     *
     * @param tag the value for info.tag
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column info.description
     *
     * @return the value of info.description
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column info.description
     *
     * @param description the value for info.description
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}