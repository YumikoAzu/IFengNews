package com.yumikoazu.ifengnews.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joker on 2015/9/11.
 */
public class NewsSlideItem {



    private MetaEntity meta;
    private BodyEntity body;

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public BodyEntity getBody() {
        return body;
    }

    public static class MetaEntity {


        private String id;
        private String type;
        private int o;
        private String documentId;
        @SerializedName("class")
        private String classX;

        public void setId(String id) {
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setO(int o) {
            this.o = o;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public int getO() {
            return o;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getClassX() {
            return classX;
        }
    }

    public static class BodyEntity {


        private String url;
        private String wwwurl;
        private String commentsUrl;
        private String documentId;
        private String shareurl;
        private String source;
        private String title;
        private String text;
        private PreEntity pre;
        private NextEntity next;
        private String cate;
        private String editTime;
        private List<SlidesEntity> slides;
        private List<RecommendEntity> recommend;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWwwurl(String wwwurl) {
            this.wwwurl = wwwurl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setPre(PreEntity pre) {
            this.pre = pre;
        }

        public void setNext(NextEntity next) {
            this.next = next;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public void setEditTime(String editTime) {
            this.editTime = editTime;
        }

        public void setSlides(List<SlidesEntity> slides) {
            this.slides = slides;
        }

        public void setRecommend(List<RecommendEntity> recommend) {
            this.recommend = recommend;
        }

        public String getUrl() {
            return url;
        }

        public String getWwwurl() {
            return wwwurl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getShareurl() {
            return shareurl;
        }

        public String getSource() {
            return source;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public PreEntity getPre() {
            return pre;
        }

        public NextEntity getNext() {
            return next;
        }

        public String getCate() {
            return cate;
        }

        public String getEditTime() {
            return editTime;
        }

        public List<SlidesEntity> getSlides() {
            return slides;
        }

        public List<RecommendEntity> getRecommend() {
            return recommend;
        }

        public static class PreEntity {


            private String id;
            private String title;
            private String type;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }
        }

        public static class NextEntity {


            private String id;
            private String title;
            private String type;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }
        }

        public static class SlidesEntity {


            private String image;
            private String title;
            private String description;

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }
        }

        public static class RecommendEntity {


            private String title;
            private String id;
            private String type;
            private String links;
            private String thumbnail;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setLinks(String links) {
                this.links = links;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getTitle() {
                return title;
            }

            public String getId() {
                return id;
            }

            public String getType() {
                return type;
            }

            public String getLinks() {
                return links;
            }

            public String getThumbnail() {
                return thumbnail;
            }
        }
    }
}
