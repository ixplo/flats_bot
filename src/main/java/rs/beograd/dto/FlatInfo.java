package rs.beograd.dto;

public class FlatInfo {
    private String id;
    private String url;
    private String price;
    private String publishDate;

    public FlatInfo() {
    }

    public FlatInfo(String id, String url, String price, String publishDate) {
        this.id = id;
        this.url = url;
        this.price = price;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "FlatInfo{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", price='" + price + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
