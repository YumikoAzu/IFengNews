package com.yumikoazu.ifengnews.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joker on 2015/9/11.
 */
public class NewsDocItem {



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


        private String cate;
        private String documentId;
        private String title;
        private String author;
        private String hasVideo;
        private String source;
        private String thumbnail;
        private String editTime;
        private String wapurl;
        private String channel;
        private String introduction;
        private String wwwurl;
        private String shareurl;
        private String commentsUrl;
        private String commentCount;
        private String text;
        private String program;
        private String programNo;
        private String sologan;
        private String commentType;
        private List<ImgEntity> img;

        public void setCate(String cate) {
            this.cate = cate;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setHasVideo(String hasVideo) {
            this.hasVideo = hasVideo;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setEditTime(String editTime) {
            this.editTime = editTime;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setWwwurl(String wwwurl) {
            this.wwwurl = wwwurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public void setProgramNo(String programNo) {
            this.programNo = programNo;
        }

        public void setSologan(String sologan) {
            this.sologan = sologan;
        }

        public void setCommentType(String commentType) {
            this.commentType = commentType;
        }

        public void setImg(List<ImgEntity> img) {
            this.img = img;
        }

        public String getCate() {
            return cate;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getHasVideo() {
            return hasVideo;
        }

        public String getSource() {
            return source;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getEditTime() {
            return editTime;
        }

        public String getWapurl() {
            return wapurl;
        }

        public String getChannel() {
            return channel;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getWwwurl() {
            return wwwurl;
        }

        public String getShareurl() {
            return shareurl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public String getText() {
            return text;
        }

        public String getProgram() {
            return program;
        }

        public String getProgramNo() {
            return programNo;
        }

        public String getSologan() {
            return sologan;
        }

        public String getCommentType() {
            return commentType;
        }

        public List<ImgEntity> getImg() {
            return img;
        }

        public static class ImgEntity {


            private String url;
            private SizeEntity size;

            public void setUrl(String url) {
                this.url = url;
            }

            public void setSize(SizeEntity size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public SizeEntity getSize() {
                return size;
            }

            public static class SizeEntity {
                /**
                 * width : 320
                 * height : 320
                 */

                private String width;
                private String height;

                public void setWidth(String width) {
                    this.width = width;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public String getHeight() {
                    return height;
                }
            }
        }
    }
}
