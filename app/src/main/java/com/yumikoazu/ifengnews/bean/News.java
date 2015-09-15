package com.yumikoazu.ifengnews.bean;

import java.util.List;

/**
 * Created by joker on 2015/9/9.
 */
public class News {


    private String listId;
    private String type;
    private int expiredTime;
    private int currentPage;
    private int totalPage;
    private List<ItemEntity> item;

    public void setListId(String listId) {
        this.listId = listId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    public String getListId() {
        return listId;
    }

    public String getType() {
        return type;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public static class ItemEntity {


        private String thumbnail;
        private String title;
        private String id;
        private String documentId;
        private String type;
        private String commentsUrl;
        private String comments;
        private String commentsall;
        private LinkEntity link;
        private StyleEntity style;

        private String source;
        private String updateTime;

        public StyleEntity getStyle() {
            return style;
        }

        public void setStyle(StyleEntity style) {
            this.style = style;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }


        public void setTitle(String title) {
            this.title = title;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }


        public void setLink(LinkEntity link) {
            this.link = link;
        }

        public String getThumbnail() {
            return thumbnail;
        }


        public String getTitle() {
            return title;
        }

        public String getId() {
            return id;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getType() {
            return type;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getComments() {
            return comments;
        }

        public String getCommentsall() {
            return commentsall;
        }


        public LinkEntity getLink() {
            return link;
        }

        public static class LinkEntity {


            private String type;
            private String url;

            public void setType(String type) {
                this.type = type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }


            public LinkEntity() {
            }


        }

        public static class StyleEntity {


            private String type;
            private List<String> images;

            public void setType(String type) {
                this.type = type;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public String getType() {
                return type;
            }

            public List<String> getImages() {
                return images;
            }


            public StyleEntity() {
            }


        }


        public ItemEntity() {
        }


    }


    public News() {
    }


}
