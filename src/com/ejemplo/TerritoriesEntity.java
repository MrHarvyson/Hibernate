package com.ejemplo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "territories", schema = "northwind", catalog = "")
public class TerritoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TerritoryID")
    private String territoryId;
    @Basic
    @Column(name = "TerritoryDescription")
    private String territoryDescription;
    @Basic
    @Column(name = "RegionID")
    private int regionId;
    @OneToMany(mappedBy = "territoriesByTerritoryId")
    private Collection<EmployeeterritoriesEntity> employeeterritoriesByTerritoryId;
    @ManyToOne
    @JoinColumn(name = "RegionID", referencedColumnName = "RegionID", nullable = false, insertable=false, updatable=false)
    private RegionEntity regionByRegionId;

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerritoriesEntity that = (TerritoriesEntity) o;
        return regionId == that.regionId && Objects.equals(territoryId, that.territoryId) && Objects.equals(territoryDescription, that.territoryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(territoryId, territoryDescription, regionId);
    }

    public Collection<EmployeeterritoriesEntity> getEmployeeterritoriesByTerritoryId() {
        return employeeterritoriesByTerritoryId;
    }

    public void setEmployeeterritoriesByTerritoryId(Collection<EmployeeterritoriesEntity> employeeterritoriesByTerritoryId) {
        this.employeeterritoriesByTerritoryId = employeeterritoriesByTerritoryId;
    }

    public RegionEntity getRegionByRegionId() {
        return regionByRegionId;
    }

    public void setRegionByRegionId(RegionEntity regionByRegionId) {
        this.regionByRegionId = regionByRegionId;
    }
}
