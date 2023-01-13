package com.ejemplo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employeeterritories", schema = "northwind", catalog = "")
@IdClass(EmployeeterritoriesEntityPK.class)
public class EmployeeterritoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EmployeeID")
    private int employeeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TerritoryID")
    private String territoryId;
    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", nullable = false, insertable=false, updatable=false)
    private EmployeesEntity employeesByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "TerritoryID", referencedColumnName = "TerritoryID", nullable = false, insertable=false, updatable=false)
    private TerritoriesEntity territoriesByTerritoryId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeterritoriesEntity that = (EmployeeterritoriesEntity) o;
        return employeeId == that.employeeId && Objects.equals(territoryId, that.territoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, territoryId);
    }

    public EmployeesEntity getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(EmployeesEntity employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    public TerritoriesEntity getTerritoriesByTerritoryId() {
        return territoriesByTerritoryId;
    }

    public void setTerritoriesByTerritoryId(TerritoriesEntity territoriesByTerritoryId) {
        this.territoriesByTerritoryId = territoriesByTerritoryId;
    }
}
